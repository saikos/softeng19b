import React, { Component } from 'react';
import $ from 'jquery';

export default class UserConfirmationModal extends Component {        
        
    constructor(props) {
        super(props);                
        this.$modalDOMElem = null;
        this.userChoice = false; //default is no
        this.modalMounted = this.modalMounted.bind(this);
        this.handleYes = this.handleYes.bind(this);
    }
    
    //example of using a React ref as DOM element mount/unmount callback
    modalMounted(refToModal) {
        const hideFun = () => {
           this.props.onHide(this.userChoice); 
        };
        console.log('ref to modal: ', refToModal);
        if (refToModal !== null) {
            this.$modalDOMElem = $(refToModal);
            this.$modalDOMElem.modal('show');
            this.$modalDOMElem.on('hidden.bs.modal', hideFun.bind(this));
        }        
    }    
    
    handleYes() {
        this.userChoice = true;
        this.$modalDOMElem.modal('hide');
    }
    
    render() {
        
        console.log('render modal');
        
        if (this.props.visible === true) {            
            console.log('visible modal');
            return (
                <div className="modal fade" ref={this.modalMounted}>
                    <div className="modal-dialog" role="document">
                      <div className="modal-content">
                        <div className="modal-header">
                          <h5 className="modal-title">{this.props.title}</h5>
                          <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                          </button>
                        </div>
                        <div className="modal-body">
                          {this.props.message}
                        </div>
                        <div className="modal-footer">
                          <button type="button" className="btn btn-secondary" data-dismiss="modal">No</button>
                          <button type="button" className="btn btn-primary" onClick={this.handleYes}>Yes</button>
                        </div>
                      </div>
                    </div>
                </div>                
            );
        }
        else {
            return null;
        }
    }
    
}

