package uk.ac.sanger.scgcf.barcodegenerator.api;

@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

public class NotFoundException extends ApiException {
    
    private static final long serialVersionUID = -5524892422386102843L;
    
    private int code;
    
    public NotFoundException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
