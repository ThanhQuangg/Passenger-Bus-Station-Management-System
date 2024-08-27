import { useEffect, useState, useContext } from "react";
import { Badge, Button, Col, Container, Form, Image, Nav, Navbar, NavDropdown, Row } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import APIs, { endpoints } from "../../configs/APIs";
import MySpinner from "./MySpinner";
import { MyDispatchContext, MyUserContext, MyCartContext } from "../../configs/Contexts";

const Header = () => {
    const [categories, setCategories] = useState([]);
    const [q, setQ] = useState("");
    const [departure, setDeparture] = useState("");
    const [destination, setDestination] = useState("");
    const nav = useNavigate();
    const user = useContext(MyUserContext);
    const dispatch = useContext(MyDispatchContext);
    const { getCartCount } = useContext(MyCartContext);
    const [cartCount, setCartCount] = useState(getCartCount());

    useEffect(() => {
        loadCategories();
    }, []);

    const loadCategories = async () => {
        try {
            const res = await APIs.get(endpoints['categories']);
            setCategories(res.data);
        } catch (ex) {
            console.error(ex);
        }
    };

    const searchByCategory = (e, cateID) => {
        e.preventDefault();
        nav(`/?cateID=${cateID}`);
    };

    const searchTrips = (e) => {
        e.preventDefault();
        nav(`/?q=${q}`);
    };

    // const searchTrip = (e) => {
    //     e.preventDefault();
    //     nav(`/?departure=${departure}&destination=${destination}`);
    // };

    

    const showDanhMuc = user?.userRole === "ROLE_ADMIN" || user?.userRole === "ROLE_COMPANY";

    useEffect(() => {
        setCartCount(getCartCount());
    }, [getCartCount]);
    return (
        <>
            {categories === null ? <MySpinner /> :
                <Navbar expand="lg" className="bg-body-tertiary">
                    <Container>
                        <Navbar.Brand href="/">QUAN LY BEN XE</Navbar.Brand>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="me-auto">
                                <Link className="nav-link" to="/" >Trang chủ</Link>
                                <Link className="nav-link" to="/gioi-thieu" >Giới thiệu</Link>
                                {showDanhMuc && (
                                    <NavDropdown title="Danh mục" id="basic-nav-dropdown">
                                        <NavDropdown.Item href="#" onClick={() => nav("/buses")}>Bus</NavDropdown.Item>
                                        <NavDropdown.Item href="#" onClick={() => nav("/cates")}>Category</NavDropdown.Item>
                                        <NavDropdown.Item href="#" onClick={() => nav("/companies")}>Company</NavDropdown.Item>
                                        <NavDropdown.Item href="#" onClick={() => nav("/routes")}>Route</NavDropdown.Item>
                                        <NavDropdown.Item href="#" onClick={() => nav("/trips")}>Trip</NavDropdown.Item>
                                        <NavDropdown.Item href="#" onClick={() => nav("/users")}>User</NavDropdown.Item>
                                    </NavDropdown>
                                )}
                                <NavDropdown title="Loại xe" id="basic-nav-dropdown">
                                    {categories.map(c => (
                                        <NavDropdown.Item key={c.categoryID} href="#" onClick={e => searchByCategory(e, c.categoryID)}>
                                            {c.name}
                                        </NavDropdown.Item>
                                    ))}
                                </NavDropdown>
                                <Link className="nav-link" to="/carts">
                                    &#128722; <Badge bg="danger">{getCartCount()}</Badge>
                                </Link>
                                {user === null ? (
                                    <>
                                        <Link to="/register" className="nav-link text-success">Đăng ký</Link>
                                        <Link to="/login" className="nav-link text-info">Đăng nhập</Link>
                                    </>
                                ) : (
                                    <>
                                        <Link to="/" className="nav-link text-success">
                                            <Image src={user.avatar} width="40" roundedCircle /> {user.username}
                                        </Link>
                                        <Link onClick={() => dispatch({ "type": "logout" })} className="nav-link">Đăng xuất</Link>
                                    </>
                                )}
                            </Nav>
                        </Navbar.Collapse>
                        <Form inline onSubmit={searchTrips} className="m-2">
                            <Row>
                                <Col xs="auto">
                                    <Form.Control type="text" value={q} onChange={e => setQ(e.target.value)} placeholder="Tìm chuyến xe..." className="mr-sm-2" />
                                </Col>
                                <Col xs="auto">
                                    <Button type="submit">Tìm</Button>
                                </Col>
                            </Row>
                        </Form>
                        {/* <Form inline onSubmit={searchTrip} className="m-2">
                            <Row>
                                <Col xs="auto">
                                    <Form.Control
                                        type="text"
                                        value={departure}
                                        onChange={e => setDeparture(e.target.value)}
                                        placeholder="Điểm đi"
                                        className="mr-sm-2"
                                    />
                                </Col>
                                <Col xs="auto">
                                    <Form.Control
                                        type="text"
                                        value={destination}
                                        onChange={e => setDestination(e.target.value)}
                                        placeholder="Điểm đến"
                                        className="mr-sm-2"
                                    />
                                </Col>
                                <Col xs="auto">
                                    <Button type="submit">Tìm</Button>
                                </Col>
                            </Row>
                        </Form> */}

                    </Container>
                </Navbar>
            }
        </>
    );
}

export default Header;
