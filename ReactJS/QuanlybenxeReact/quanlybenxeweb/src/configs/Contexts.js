import { createContext, useState, useEffect, useContext } from "react";
import cookie from "react-cookies";

export const MyUserContext = createContext();
export const MyDispatchContext = createContext();

const MyCartContext = createContext();

export const MyCartProvider = ({ children }) => {
    const [cart, setCart] = useState(cookie.load('cart') || {});

    // Cập nhật giỏ hàng khi cookie thay đổi
    useEffect(() => {
        const interval = setInterval(() => {
            const storedCart = cookie.load('cart') || {};
            setCart(storedCart);
        }, 0); 

        return () => clearInterval(interval); // Dọn dẹp khi component unmount
    }, []);

    // Hàm để lấy số lượng hàng trong giỏ hàng
    const getCartCount = () => {
        return Object.values(cart).reduce((total, item) => total + item.quantity, 0);
    };

    // Hàm để cập nhật giỏ hàng
    const updateCart = (newCart) => {
        setCart(newCart);
        cookie.save('cart', newCart); // Lưu giỏ hàng vào cookie
    };

    return (
        <MyCartContext.Provider value={{ cart, setCart: updateCart, getCartCount }}>
            {children}
        </MyCartContext.Provider>
    );
};
export const useCart = () => useContext(MyCartContext);