import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import { Button } from 'reactstrap';
import './auth.scss';

class Registration extends Component {
    render() {
        return (
            <Grid
                className="wrapper"
                container
                direction="column"
                justify="center"
                alignItems="center"
            >
                <div className="sign-in">Registration</div>
                <form className="form-login">
                    <Grid
                        container
                        spacing={16}
                        direction="column"
                        justify="center"
                        alignItems="center"

                    >
                        <TextField
                            id="outlined-email-input"
                            label="Email"
                            type="email"
                            name="email"
                            autoComplete="email"
                            margin="normal"
                            variant="outlined"
                            fullWidth
                        />
                        <TextField
                            id="outlined-password-input"
                            label="Password"
                            type="password"
                            autoComplete="current-password"
                            margin="normal"
                            variant="outlined"
                            fullWidth
                        />
                            <p className="text-left">Уже зарегистрированны?
                                <a href="./login"> Залогиниться</a>
                            </p>
                        <Button
                            className="form-login-btn-registration"
                            color="success"
                            size="lg"
                            block
                        >
                            Registration
                        </Button>
                    </Grid>
                </form>
            </Grid>
        );
    }
};

export  default  Registration;
