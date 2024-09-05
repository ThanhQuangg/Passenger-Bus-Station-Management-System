import { useState, useEffect } from "react";
import { Alert, Button, Container, Table } from "react-bootstrap";
import cookie from "react-cookies";
import APIs, { endpoints } from "../../../configs/APIs";
import { useNavigate } from "react-router-dom";

const Cart = () => {
    const [cart, setCart] = useState(cookie.load('cart') || null);
    const [pay, setPay] = useState({});
    const nav = useNavigate();

    useEffect(() => {
        const handleLogout = () => {
            setCart(null);
        };

        window.addEventListener("logout", handleLogout);

        return () => {
            window.removeEventListener("logout", handleLogout);
        };
    }, []);

    const addPay = async (e, item) => {
        e.preventDefault();

        const payData = {
            cart1: {
                id: item.id,
                quantity: item.quantity,
                price: parseFloat(item.price),
            }
        };

        try {
            let res = await APIs.post(endpoints['pay'], JSON.stringify(payData), {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': ` ${cookie.load('token')}`
                }
            });

            if (res.status === 200) {
                const updatedCart = { ...cart }; // Tạo bản sao của cart
                delete updatedCart[item.id]; // Xóa sản phẩm đã thanh toán khỏi cart

                // Cập nhật cookie và state với giỏ hàng mới
                cookie.save('cart', updatedCart);
                setCart(updatedCart);

                // Điều hướng về trang chủ
                nav("/carts");
            }
        } catch (ex) {
            console.error(ex);
        }
    }


    return (
        <Container>
            <h1 className="text-center text-info mt-1">GIỎ HÀNG</h1>
            {cart === null ? (
                <Alert variant="danger">Không có sản phẩm nào trong giỏ!</Alert>
            ) : (
                <>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên chuyến đi</th>
                                <th>Đơn giá</th>
                                <th>Số lượng</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {Object.values(cart).map(c => (
                                <tr key={c.id}>
                                    <td>{c.id}</td>
                                    <td>{c.name}</td>
                                    <td>{c.price} VNĐ</td>
                                    <td>{c.quantity}</td>
                                    <td>
                                        <Button variant="danger">&times;</Button>
                                    </td>
                                    <td>
                                        <Button variant="success" onClick={(e) => addPay(e, c)}>
                                            Thanh toán
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </>
            )}
        </Container>
    );
}

export default Cart;
