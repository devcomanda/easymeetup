import React, {Component, Fragment} from 'react';
import { Route } from 'react-router-dom';

const Home = () => (
    <h1>Дом, милый дом!</h1>
);

const Profile = () => (
    <h1>Мой профиль!</h1>
);

class Base extends Component {
    render() {
        return (
            <Fragment>
                <header>Заголовк</header>
                <main>
                        <Route exact path='/' component={Home}/>
                        <Route path='/profile' component={Profile}/>
                </main>
                <footer>Подвал</footer>
            </Fragment>
        );
    }
}

export default Base;