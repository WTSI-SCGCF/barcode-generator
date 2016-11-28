package uk.ac.sanger.scgcf.barcodegenerator.controller;

@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

public class ApiException extends Exception {
    
    private static final long serialVersionUID = 5471376971182240449L;
    
    private int code;
    
    public ApiException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
