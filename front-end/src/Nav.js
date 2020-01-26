import React, { Component } from 'react';
import { Link, withRouter } from "react-router-dom";
import { UserConsumer } from './UserContext';

const css = {
    fullWidth: {
        width:'100%'
    }
}

/*
 * This is a React component, realized as function.
 */
const NavLink = props => {    
    const link = <Link className="nav-link" to={props.to}>{props.label}</Link>;
    if (props.to === props.location) {
        return <li className="nav-item active">{link}</li>
    }
    else {
        return <li className="nav-item">{link}</li>
    }    
}

/*
 * This is the same as above, more verbose.
 */
class NavMenu extends Component {
    render() {
        console.log('Rendering menu for user: ', this.props.context.username);
        if (this.props.context.username) {
            return (
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">               
                        <NavLink label="Home" to="/main" location={this.props.location.pathname} />
                        <NavLink label="Logout" to='/logout' location={this.props.location.pathname} />
                    </ul>                                      
                </div>
            );
        }
        else {
            return (
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav mr-auto">               
                        <NavLink label="Login" to="/login" location={this.props.location.pathname} />                        
                    </ul>                                      
                </div>                
            );
        }
    }
}

/*
 * Back to minimum verbosity!
 */
const UserAvatar = props => {
    if (props.context.username) {
        return (
            <span className='navbar-text'>{props.context.username}</span>
        );
    }
    else {
        return null;
    }
}

class Nav extends Component {    
    
    render() {
        return (            
            <div className="row">
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark" style={css.fullWidth}>
                    <Link className="navbar-brand" to="/">Front-end</Link>
                    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <UserConsumer>
                    { context => 
                        <React.Fragment>
                            <NavMenu 
                                location={this.props.location} 
                                context={context}
                            />
                            <UserAvatar context={context} />
                        </React.Fragment>
                    }
                    </UserConsumer>
                </nav>
            </div>
        );
    }
    
}

export default withRouter(Nav);
