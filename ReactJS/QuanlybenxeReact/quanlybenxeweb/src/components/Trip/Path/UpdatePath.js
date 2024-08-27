import { useState, useEffect } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const UpdatePath = () => {
    const fields = [{
        label: "Tên tuyến đường",
        type: "text",
        field: "name"
    }, {
        label: "Điểm xuất phát",
        type: "text",
        field: "startLocation"
    }, {
        label: "Điểm đến",
        type: "text",
        field: "endLocation"
    }, {
        label: "Khoảng cách",
        type: "text",
        field: "distance"
    }, {
        label: "Giá vé",
        type: "text",
        field: "ticketPrice"
    }, {
        label: "Khoảng thời gian di chuyển dự kiến",
        type: "text",
        field: "estimatedDuration"
    }, {
        label: "Mô tả",
        type: "text",
        field: "description"
    }];

    const [path, setPath] = useState({
        name: '',
        startLocation:'',
        endLocation:'',
        distance:'',
        ticketPrice:'',
        estimatedDuration:'',
        description: ''
    });
    const nav = useNavigate();
    const { routeID } = useParams();

    useEffect(() => {
        const loadPath = async () => {
            try {
                let res = await APIs.get(`${endpoints['routes']}${routeID}`);
                setPath(res.data);
            } catch (ex) {
                console.error(ex);
            }
        };
        loadPath();
    }, [routeID]);

    const change = (e, field) => {
        setPath(current => ({
            ...current,
            [field]: e.target.value
        }));
    }

    const updatePath = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in path) {
            if (key !== 'confirm') {
                form.append(key, path[key]);
            }
        }

        try {
            let res = await APIs.put(`${endpoints['routes']}${routeID}`, form, {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `${cookie.load('token')}`
                }
            });
            if (res.status === 200) {
                nav("/routes");
            }
        } catch (ex) {
            console.error(ex);
        }
    }

    return (
        <Container>
            <h1 className="text-center text-info mt-1">CẬP NHẬT TUYẾN ĐƯỜNG</h1>
            <Form onSubmit={updatePath}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control
                            onChange={e => change(e, f.field)}
                            value={path [f.field]}
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

export default UpdatePath;
