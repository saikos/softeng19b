import React from 'react';

export const UserContext = React.createContext();

export const UserProvider = UserContext.Provider;
export const UserConsumer = UserContext.Consumer;

export const withUserContext = function(Component) {
    return function(props) {        
        return (
            <UserConsumer>
            { context => 
                <Component context={context} {...props} />
            }
            </UserConsumer>
        );
    }
}