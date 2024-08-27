import { createContext, useState, useEffect } from "react";
import cookie from "react-cookies";

export const MyUserContext = createContext();
export const MyDispatchContext = createContext();
export const MyCartContext = createContext();


export const MyCartProvider = ({ children }) => {
    const [cart, setCart] = useState(cookie.load('cart') || {});

    const getCartCount = () => {
        return Object.values(cart).reduce((total, item) => total + item.quantity, 0);
    };

    useEffect(() => {
        setCart(cookie.load('cart') || {});
    }, []);

    return (
        <MyCartContext.Provider value={{  cart, setCart, getCartCount }}>
            {children}
        </MyCartContext.Provider>
    );
};
