import { useRef, useState, useEffect } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import APIs, { endpoints } from "../../../configs/APIs";
import cookie from "react-cookies";

const AddBus = () => {
    const fields = [{
        label: "Tên xe khách",
        type: "text",
        field: "name"
    }, {
        label: "Biển số xe",
        type: "text",
        field: "plateNumber"
    }, {
        label: "Sức chứa của xe",
        type: "text",
        field: "capacity"
    }];

    const [bus, setBus] = useState({});
    const [cates, setCates] = useState([]);
    const [company, setCompany] = useState([]);
    const [errors, setErrors] = useState({});
    const { busID } = useParams();  
    const nav = useNavigate();
    const avatar = useRef();

    useEffect(() => {
        const loadBus = async () => {
            if (busID) {
                const res = await APIs.get(`${endpoints['buses']}${busID}`);
                setBus(res.data);
            }
        };

        loadBus();
        loadCates();
        loadCompanies();
    }, [busID]);

    const change = (e, field) => {
        setBus(current => ({ ...current, [field]: e.target.value }));
    };

    // const validate = () => {
    //     const newErrors = {};
    //     if (!bus.name) newErrors.name = "Tên xe khách là bắt buộc";
    //     if (!bus.plateNumber) newErrors.plateNumber = "Biển số xe là bắt buộc";
    //     if (!bus.capacity || isNaN(bus.capacity)) newErrors.capacity = "Sức chứa phải là một số hợp lệ";
    //     if (!bus.categoryID) newErrors.categoryID = "Bạn phải chọn loại xe";
    //     if (!bus.companyID) newErrors.companyID = "Bạn phải chọn công ty";

    //     setErrors(newErrors);
    //     return Object.keys(newErrors).length === 0;
    // };

    const addOrUpdateBus = async (e) => {
        e.preventDefault();

        let form = new FormData();
        for (let key in bus) {
            if (key !== 'confirm') {
                form.append(key, bus[key]);
            }
        }
        
        if (avatar.current.files[0]) {
            form.append('file', avatar.current.files[0]);
        }
        
        try {
            let res;
            if (busID) {
                res = await APIs.post(`${endpoints['buses']}${busID}`, form, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Authorization': `${cookie.load('token')}`
                    }
                });
            } else {
                res = await APIs.post(endpoints['buses'], form, {
                    headers: {
                        'Content-Type': 'multipart/form-data',
                        'Authorization': `${cookie.load('token')}`
                    }
                });
            }
            if (res.status === 201 || res.status === 200) {
                nav("/buses");
            }
        } catch (ex) {
            console.error(ex);
        }
    };

    const loadCates = async () => {
        try {
            const res = await APIs.get(endpoints['categories']);
            setCates(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const loadCompanies = async () => {
        try {
            const res = await APIs.get(endpoints['companies']);
            setCompany(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    return (
        <Container>
            <h1 className="text-center text-info mt-1">
                {busID ? "CẬP NHẬT XE KHÁCH" : "ĐĂNG KÝ XE KHÁCH"}
            </h1>
            <Form onSubmit={addOrUpdateBus}>
                {fields.map(f => (
                    <Form.Group key={f.field} className="mb-3" controlId={f.field}>
                        <Form.Label>{f.label}</Form.Label>
                        <Form.Control
                            onChange={e => change(e, f.field)}
                            value={bus[f.field] || ""}
                            type={f.type}
                            placeholder={f.label}
                            isInvalid={!!errors[f.field]}
                        />
                        <Form.Control.Feedback type="invalid">
                            {errors[f.field]}
                        </Form.Control.Feedback>
                    </Form.Group>
                ))}
                <Form.Group key="categoryID" className="mb-3" controlId="categoryID">
                    <Form.Label>Loại xe</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={e => change(e, 'categoryID')}
                        value={bus['categoryID'] || ""}
                        placeholder="Chọn loại xe"
                        isInvalid={!!errors.categoryID}
                    >
                        <option value="">Chọn loại xe</option>
                        {cates.map(cate => (
                            <option key={cate.categoryID} value={cate.categoryID}>{cate.name}</option>
                        ))}
                    </Form.Control>
                    <Form.Control.Feedback type="invalid">
                        {errors.categoryID}
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group key="companyID" className="mb-3" controlId="companyID">
                    <Form.Label>Công ty</Form.Label>
                    <Form.Control
                        as="select"
                        onChange={e => change(e, 'companyID')}
                        value={bus['companyID'] || ""}
                        placeholder="Chọn công ty"
                        isInvalid={!!errors.companyID}
                    >
                        <option value="">Chọn công ty</option>
                        {company.map(company => (
                            <option key={company.companyID} value={company.companyID}>{company.companyName}</option>
                        ))}
                    </Form.Control>
                    <Form.Control.Feedback type="invalid">
                        {errors.companyID}
                    </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="avatar">
                    <Form.Label>Ảnh đại diện</Form.Label>
                    <Form.Control type="file" accept=".png,.jpg" ref={avatar} />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Button type="submit" value="primary">
                        {busID ? "Cập nhật" : "Đăng ký"}
                    </Button>
                </Form.Group>
            </Form>
        </Container>
    );
};

export default AddBus;
