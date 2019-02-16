import React, {Component} from "react";
import "bootstrap/scss/bootstrap.scss";

class AdminCms extends Component {
    render() {
        return (
            <div className="container-fluid">
                <div className="row">
                    <div className="col-2">1</div>
                    <div className="col-10">
                        <h1>Административная панель</h1>
                    </div>
                </div>
            </div>
        );
    }
}

export default AdminCms;