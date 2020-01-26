import React, { Component } from 'react';
import UserConfirmationModal from './UserConfirmationModal';

const doubler = function(i) {
    return  i * 2;
}

function tripler(i) {
    return i * 3;
}

class Footer extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            modalVisible: false
        };
        
        this.showModal = this.showModal.bind(this);
        this.hideModal = this.hideModal.bind(this);
    }
    
    showModal() {
        console.log('show modal');
        this.setState({modalVisible:true});
    }
    
    hideModal(userChoice) {
        //handle user choice
        console.log(userChoice);
        this.setState({modalVisible:false});
    }
            
    render() {
        
        const list = [1, 2, 3];
        const ld = list.map(doubler);
        const lt = list.map(tripler);     
        
        const renderedList = [];
        lt.forEach(i => {
            renderedList.push(<span key={i}>{i}</span>); 
        });
        
        return (            
            <div className='row'>
                <div className='col-lg-3'>
                    <button className="btn btn-info" onClick={this.showModal}>Confirm</button>
                    <UserConfirmationModal 
                        title="Confirm something"
                        message="Are you sure?"
                        visible={this.state.modalVisible} 
                        onHide={this.hideModal}
                    />
                </div>
                <div className='col-lg-3'>
                    {renderedList}
                </div>
                <div className='col-lg-3'>
                    {ld.join(', ')}
                </div>
                <div className='col-lg-3'>
                    {lt.map(function(i) {
                        return <b key={i}>{i}</b>;
                    })}
                </div>
            </div> 
            
        );
    }
    
}

export default Footer;
