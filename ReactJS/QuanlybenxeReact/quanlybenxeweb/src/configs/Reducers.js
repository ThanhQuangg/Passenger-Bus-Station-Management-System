import cookie from "react-cookies";

// const MyUserReducer = (current, action) => {
//     switch (action.type) {
//         case "login":
//             return action.payload;
//         case "logout":
//             cookie.remove("token");
//             return null;
//     }
//     return current;
// }

const MyUserReducer = (current, action) => {
    switch (action.type) {
        case "login":
            console.log("User logged in:", action.payload);
            return action.payload;
        case "logout":
            cookie.remove("token", { path: '/' });
            cookie.remove("cart", { path: '/' });
            cookie.remove("user", { path: '/', domain: 'localhost' });
            return null;
    }   
    return current;
}


export default MyUserReducer;   