import React from 'react';
import 'bootstrap';
import ReactDOM from 'react-dom';
import { Router } from 'react-router-dom';
import { createBrowserHistory as createHistory } from "history";
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { createStore, combineReducers, applyMiddleware} from 'redux';
import { Provider } from 'react-redux';
import thunk from "redux-thunk";
import events  from './components/Events/reducer';
import { composeWithDevTools } from 'redux-devtools-extension';

export const history = createHistory();

const rootReducer = combineReducers({
    events
}  as any );

const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(thunk))
);

ReactDOM.render(
    <Router  history={history}>
        <Provider store={store}>
            <App/>
        </Provider>        
    </Router>
, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();

console.log(store.getState());

export type ApplicationState = ReturnType<typeof rootReducer>
