import {  useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const AddPath = () => {
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
        label: "Thời gian di chuyển dự kiến",
        type: "text",
        field: "estimatedDuration"
    }, {
        label: "Mô tả",
        type: "text",
        field: "description"
    }];

    const [path, setPath] = useState([]);
    const nav = useNavigate();

    const change = (e, field) => {
        setPath(current => {
            return { ...current, [field]: e.target.value }
        })
    }

    const addPath = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in path)
            if (key !== 'confirm')
                form.append(key, path[key]);
        try {
            let res = await APIs.post(endpoints['routes'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'Authorization': `${cookie.load('token')}`
                }

            });
            if (res.status === 201)
                nav("/routes");
        } catch (ex) {
            console.error(ex);
        }
    }

    return (
        <Container>
            <h1 className="text-center text-info mt-1">ĐĂNG KÝ TUYẾN ĐƯỜNG</h1>
            <Form onSubmit={addPath}>
                {fields.map(f => <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                    <Form.Label>{f.label}</Form.Label>
                    <Form.Control onChange={e => change(e, f.field)} value={path[f.field]} type={f.type} placeholder={f.label} />
                </Form.Group>)}
                <Form.Group className="mb-3">
                    <Button type="submit" value="primary">Đăng ký</Button>
                </Form.Group>
            </Form>
        </Container>
    );
}

export default AddPath;