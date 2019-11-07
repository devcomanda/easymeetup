import React, {Component, Fragment} from 'react';
import { Route } from 'react-router-dom';
import './base.scss';

import { 
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    Button } from 'reactstrap';

const Home = () => (
    <h1>Main part</h1>
);

class Base extends Component {
    render() {
        return (
            <Fragment>
                <Navbar color="info" expand="md">
                    <NavbarBrand className="text-white" href="/">DevComanda</NavbarBrand>
                    <Nav className="ml-auto" navbar>
                        <NavItem className="link-hover">
                            <NavLink className="text-white text-uppercase margin-80" href="/events/">Events</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink className="text-white text-uppercase margin-80" href="/">Speakers</NavLink>
                        </NavItem>
                        <Button
                            className="text-uppercase"
                            color="success"
                            block
                        >Sign In</Button>
                    </Nav>
                </Navbar>
                <main>
                    <Route exact path='/' component={Home}/>
                </main>
                <footer>Подвал</footer>
            </Fragment>
        );
    }
}

export default Base;