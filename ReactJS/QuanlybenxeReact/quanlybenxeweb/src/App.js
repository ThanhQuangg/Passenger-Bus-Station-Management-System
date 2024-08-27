import { BrowserRouter, Routes, Route, Navigate  } from "react-router-dom";
import { MyCartProvider } from "./configs/Contexts";
import Home from "./components/Trip/Home/Home";
import 'bootstrap/dist/css/bootstrap.min.css';
import TripDetail from "./components/Trip/Trip/TripDetail";
import Header from "./components/Commons/Header";
import 'bootstrap/dist/css/bootstrap.min.css';
import Footer from "./components/Commons/Footer";
import BusDetail from "./components/Trip/Bus/BusDetail";
import Intro from "./components/Trip/Intro/Intro";
import Bus from "./components/Trip/Bus/Bus";
import Category from "./components/Trip/Category/Category";
import Company from "./components/Trip/Company/Company";
import Path from "./components/Trip/Path/Path";
import Trip from "./components/Trip/Trip/Trip";
import User from "./components/Trip/User/User";
import Cart from "./components/Trip/Cart/Cart";
import Login from "./components/User/Login";
import Register from "./components/User/Register";
import { MyDispatchContext, MyUserContext } from "./configs/Contexts";
import MyUserReducer from "./configs/Reducers";
import cookie from "react-cookies";
import { useReducer } from "react";
import AddBus from "./components/Trip/Bus/AddBus";
import AddCategory from "./components/Trip/Category/AddCategory";
import AddCompany from "./components/Trip/Company/AddCompany";
import AddPath from "./components/Trip/Path/AddPath";
import AddTrip from "./components/Trip/Trip/AddTrip";
import UpdateCategory from "./components/Trip/Category/UpdateCategory";
import UpdatePath from "./components/Trip/Path/UpdatePath";
import UpdateTrip from "./components/Trip/Trip/UpdateTrip";
const App = () => {
    const [user, dispatch] = useReducer(MyUserReducer, cookie.load("user") || null);
    return (
        <BrowserRouter>
            <MyUserContext.Provider value={user}>
                <MyDispatchContext.Provider value={dispatch}>
                    <MyCartProvider>
                        <Header />
                        <Routes>
                            <Route path="/" element={<Home />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/register" element={<Register />} />
                            <Route path="/trips/:tripID" element={<TripDetail />} />
                            <Route path="/buses/:busID" element={<BusDetail />} />
                            <Route path="/gioi-thieu" element={<Intro />} />
                            <Route path="/buses" element={<Bus />} />
                            <Route path="/cates" element={<Category />} />
                            <Route path="/companies" element={<Company />} />
                            <Route path="/routes" element={<Path />} />
                            <Route path="/trips" element={<Trip />} />
                            <Route path="/users" element={<User />} />
                            <Route path="/carts" element={<Cart />} />
                            {user?.userRole === "ROLE_ADMIN" || user?.userRole === "ROLE_COMPANY" &&  (
                                <>
                                    <Route path="/add-bus" element={<AddBus />} />
                                    <Route path="/add-cate" element={<AddCategory />} />
                                    <Route path="/add-company" element={<AddCompany />} />
                                    <Route path="/add-path" element={<AddPath />} />
                                    <Route path="/add-trip" element={<AddTrip />} />
                                    <Route path="/add-bus/:busID" element={<AddBus />} />
                                    <Route path="/update-company/:companyID" element={<AddCompany />} />
                                    <Route path="/update-cate/:categoryID" element={<UpdateCategory />} />
                                    <Route path="/update-route/:routeID" element={<UpdatePath />} />
                                    <Route path="/update-trip/:tripID" element={<UpdateTrip />} />
                                </>
                            )}
                            <Route path="*" element={<Navigate to="/login" />} />
                        </Routes>
                        <Footer />
                    </MyCartProvider>
                </MyDispatchContext.Provider>
            </MyUserContext.Provider>
        </BrowserRouter>
    );
}
export default App;