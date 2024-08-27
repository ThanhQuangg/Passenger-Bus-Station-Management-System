import { useRef, useState, useEffect } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const AddCompany = () => {
    const fields = [{
        label: "Tên nhà xe",
        type: "text",
        field: "companyName"
    }, {
        label: "Địa chỉ",
        type: "text",
        field: "address"
    }, {
        label: "Email",
        type: "text",
        field: "email"
    }, {
        label: "Số điện thoại",
        type: "text",
        field: "phoneNumber"
    }];

    const [company, setCompany] = useState({});
    const [errors, setErrors] = useState({});
    const { companyID } = useParams();  
    const nav = useNavigate();
    const avatar = useRef();

    useEffect(() => {
        const loadCompany = async () => {
            if (companyID) {
                const res = await APIs.get(`${endpoints['companies']}${companyID}`);
                setCompany(res.data);
            }
        };

        loadCompany();
    }, [companyID]);

    const change = (e, field) => {
        setCompany(current => ({ ...current, [field]: e.target.value }));
    };

    const validate = () => {
        const newErrors = {};
        if (!company.companyName) newErrors.companyName = "Tên nhà xe là bắt buộc";
        if (!company.address) newErrors.address = "Địa chỉ là bắt buộc";
        if (!company.email) newErrors.email = "Email là bắt buộc";
        if (!company.phoneNumber) newErrors.phoneNumber = "Số điện thoại là bắt buộc";

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const addOrUpdateCompany = async (e) => {
        e.preventDefault();

        if (!validate()) return;

        let form = new FormData();
        for (let key in company) {
            if (key !== 'confirm') {
                form.append(key, company[key]);
            }
        }
        
        if (avatar.current.files[0]) {
            form.append('file', avatar.current.files[0]);
        }
        
        try {
            let res;
            if (companyID) {
                res = await APIs.post(`${endpoints['companies']}${companyID}`, form, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Authorization': `${cookie.load('token')}`
                    }
                });
            } else {
                res = await APIs.post(endpoints['companies'], form, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Authorization': `${cookie.load('token')}`
                    }
                });
            }
            if (res.status === 201 || res.status === 200) {
                nav("/companies");
            }
        } catch (ex) {
            console.error(ex);
        }
    };

    return (
        <Container>
            <h1 className="text-center text-info mt-1">
                {companyID ? "CẬP NHẬT NHÀ XE" : "ĐĂNG KÝ NHÀ XE"}
            </h1>
            <Form onSubmit={addOrUpdateCompany}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control
                            onChange={e => change(e, f.field)}
                            value={company[f.field] || ""}
                            type={f.type}
                            placeholder={f.label}
                            isInvalid={!!errors[f.field]}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors[f.field]}
                        </Form.Control.Feedback>
                    </Form.Group>
                ))}
                <Form.Group className="mb-3" controlId="isShippingAvailable">
                    <Form.Label>Nhận giao hàng</Form.Label>
                    <Form.Select
                        onChange={e => change(e, 'isShippingAvailable')}
                        value={company['isShippingAvailable'] || ""}
                        isInvalid={!!errors.isShippingAvailable}
                    >
                        <option value="">Chọn tùy chọn</option>
                        <option value="true">Có</option>
                        <option value="false">Không</option>
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">
                        {errors.isShippingAvailable}
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="avatar">
                    <Form.Label>Ảnh đại diện</Form.Label>
                    <Form.Control type="file" accept=".png,.jpg" ref={avatar} />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit" value="primary">
                        {companyID ? "Cập nhật" : "Đăng ký"}
                    </Button>
                </Form.Group>
            </Form>
        </Container>
    );
};

export default AddCompany;
