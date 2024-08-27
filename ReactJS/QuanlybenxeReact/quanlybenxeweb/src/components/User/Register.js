import { useRef, useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";

const Register = () => {
    const fields = [{
        label: "Tên người dùng",
        type: "text",
        field: "firstName"
    }, {
        label: "Họ và tên lót",
        type: "text",
        field: "lastName"
    }, {
        label: "Email",
        type: "email",
        field: "email"
    }, {
        label: "Điện thoại",
        type: "tel",
        field: "phoneNumber"
    }, {
        label: "Tên đăng nhập",
        type: "text",
        field: "username"
    }, {
        label: "Mật khẩu",
        type: "password",
        field: "password"
    }, {
        label: "Xác nhận mật khẩu",
        type: "password",
        field: "confirm"
    }];

    const [user, setUser] = useState({});
    const avatar = useRef();
    const nav = useNavigate();

    const change = (e, field) => {
        setUser(current => {
            return { ...current, [field]: e.target.value }
        })
    }

    const register = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in user)
            if (key !== 'confirm')
                form.append(key, user[key]);

        if (avatar)
            form.append('file', avatar.current.files[0]);

        try {
            let res = await APIs.post(endpoints['register'], form, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });
            if (res.status === 201)
                nav("/login");
        } catch (ex) {
            console.error(ex);
        }
    }

    return (
        <Container>
            <h1 className="text-center text-info mt-1">ĐĂNG KÝ NGƯỜI DÙNG</h1>
            <Form onSubmit={register}>
                {fields.map(f => <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                    <Form.Label>{f.label}</Form.Label>
                    <Form.Control onChange={e => change(e, f.field)} value={user[f.field]} type={f.type} placeholder={f.label} />
                </Form.Group>)}
                <Form.Group className="mb-3" controlId="userRole">
                    <Form.Label>Vai trò người dùng</Form.Label>
                    <Form.Select
                        onChange={e => change(e, 'userRole')}
                        value={user['userRole'] || ''}>
                        <option value="">Chọn vai trò</option>
                        <option value="ROLE_USER">Người dùng</option>
                        <option value="ROLE_ADMIN">Quản trị viên</option>
                        <option value="ROLE_COMPANY">NHÀ XE</option>
                    </Form.Select>
                </Form.Group>

                <Form.Group className="mb-3" controlId="avatar">
                    <Form.Label>Ảnh đại diện</Form.Label>
                    <Form.Control type="file" accept=".png,.jpg" ref={avatar} />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit" value="primary">Đăng ký</Button>
                </Form.Group>
            </Form>
        </Container>
    );
}

export default Register;