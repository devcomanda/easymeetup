import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter} from 'react-router-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { Provider } from 'react-redux';

import {createStore} from 'redux';
import { rootReducer } from './rootReducer';

// const eventState = {
//     events: [
//         {
//             id: 1212,
//             title: "CopyRight Automatico 1",
//             address: "Kiev, Main street 15",
//             date: "12.12.2018",
//             description: "Description Js",
//             speaker: "Ilon Mask"
//         },
//         {
//             id: 1213,
//             title: "CopyRight Automatico 1",
//             address: "Kiev, Main street 15",
//             date: "12.12.2018",
//             description: "Description Js",
//             speaker: "Ilon Mask"
//         },
//         {
//             id: 1215,
//             title: "CopyRight Automatico 1",
//             address: "Kiev, Main street 15",
//             date: "12.12.2018",
//             description: "Description Js",
//             speaker: "Ilon Mask"
//         }
//     ]
// };

const store = createStore(
    rootReducer,
    (window as any).__REDUX_DEVTOOLS_EXTENSION__ &&
    (window as any).__REDUX_DEVTOOLS_EXTENSION__()
);


ReactDOM.render(
    <Provider store = {store}>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </Provider>
, document.getElementById('root'));

store.subscribe(() => console.log(store.getState()));
//


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
