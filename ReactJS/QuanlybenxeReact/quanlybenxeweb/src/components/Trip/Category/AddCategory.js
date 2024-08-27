import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const AddCategory = () => {
    const fields = [{
        label: "Tên loại xe",
        type: "text",
        field: "name"
    }, {
        label: "Mô tả",
        type: "text",
        field: "description"
    }];

    const [cates, setCates] = useState({});
    const [errors, setErrors] = useState({});
    const nav = useNavigate();

    const change = (e, field) => {
        setCates(current => {
            return { ...current, [field]: e.target.value }
        })
    }

    const validate = () => {
        const newErrors = {};
        if (!cates.name) newErrors.name = "Tên loại xe là bắt buộc";
        if (!cates.description) newErrors.description = "Mô tả là bắt buộc";

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const addCate = async (e) => {
        e.preventDefault();

        if (!validate()) return;

        let form = new FormData();
        for (let key in cates)
            if (key !== 'confirm')
                form.append(key, cates[key]);

        try {
            let res = await APIs.post(endpoints['categories'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `${cookie.load('token')}`
                }
            });
            if (res.status === 201)
                nav("/cates");
        } catch (ex) {
            console.error(ex);
        }
    }

    return (
        <Container>
            <h1 className="text-center text-info mt-1">ĐĂNG KÝ LOẠI XE</h1>
            <Form onSubmit={addCate}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control
                            onChange={e => change(e, f.field)}
                            value={cates[f.field] || ""}
                            type={f.type}
                            placeholder={f.label}
                            isInvalid={!!errors[f.field]}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors[f.field]}
                        </Form.Control.Feedback>
                    </Form.Group>
                ))}
                <Form.Group className="mb-3">
                    <Button type="submit" value="primary">Đăng ký</Button>
                </Form.Group>
            </Form>
        </Container>
    );
}

export default AddCategory;
