import React, { Component } from 'react';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import { Button } from 'reactstrap';
import './auth.scss';

class Login extends Component {
    render() {
        return (
            <Grid
                xs={6}
                align-items-xs-center
                container
                direction="row"
                justify="space-around"
                // alignItems="center"
            >
                <form className="form-login">
                    <Grid
                        container
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
                        />
                        <TextField
                            id="outlined-password-input"
                            label="Password"
                            type="password"
                            autoComplete="current-password"
                            margin="normal"
                            variant="outlined"
                        />  
                        <Button color="success">REGISTRATION</Button>
                    </Grid>                  
                </form>
            </Grid>
        );
    }
};

export  default  Login;