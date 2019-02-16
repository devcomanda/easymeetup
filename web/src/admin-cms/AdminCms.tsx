import React, {Component} from "react";
import "bootstrap/scss/bootstrap.scss";
import RightMenu from "./RightMenu";
import HeadPanel from "./HeadPanel";

class AdminCms extends Component {

    render() {

        return (
            <div className="container-fluid">
                <div className="row" style={{marginBottom: "15px"}}>
                    <div className="col-12">
                        <HeadPanel />
                    </div>
                </div>
                <div className="row">
                    <div className="col-2">
                        <RightMenu/>
                    </div>
                    <div className="col-10">
                    </div>
                </div>
            </div>
        );
    }
}

export default AdminCms;