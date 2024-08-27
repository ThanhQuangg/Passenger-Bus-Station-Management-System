import { useState, useEffect } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const UpdateCategory = () => {
    const fields = [{
        label: "Tên loại xe",
        type: "text",
        field: "name"
    }, {
        label: "Mô tả",
        type: "text",
        field: "description"
    }];

    const [cates, setCates] = useState({
        // name: '',
        // description: ''
    });
    const nav = useNavigate();
    const { categoryID } = useParams();

    useEffect(() => {
        const loadCates = async () => {
            try {
                let res = await APIs.get(`${endpoints['categories']}${categoryID}`);
                setCates(res.data);
            } catch (ex) {
                console.error(ex);
            }
        };
        loadCates();
    }, [categoryID]);

    const change = (e, field) => {
        setCates(current => ({
            ...current,
            [field]: e.target.value
        }));
    }

    const updateCate = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in cates) {
            if (key !== 'confirm') {
                form.append(key, cates[key]);
            }
        }

        try {
            let res = await APIs.put(`${endpoints['categories']}${categoryID}`, form, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `${cookie.load('token')}`
                }
            });
            if (res.status === 200) {
                nav("/cates");
            }
        } catch (ex) {
            console.error(ex);
        }
    }

    return (
        <Container>
            <h1 className="text-center text-info mt-1">CẬP NHẬT LOẠI XE</h1>
            <Form onSubmit={updateCate}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control
                            onChange={e => change(e, f.field)}
                            value={cates[f.field]}
                            type={f.type}
                            placeholder={f.label}
                        />
                    </Form.Group>
                ))}
                <Form.Group className="mb-3">
                    <Button type="submit">CẬP NHẬT</Button>
                </Form.Group>
            </Form>
        </Container>
    );
}

export default UpdateCategory;
