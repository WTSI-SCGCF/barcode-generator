/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Error;
import uk.ac.sanger.scgcf.barcodegenerator.validators.BarcodeCreationValidator;

/**
 * Tests for <code>BarcodesApiController</code>.
 * 
 * @author ke4
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BarcodesApiController.class)
public class BarcodesApiControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BarcodeRepository barcodeRepositoryMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void readBarcodes() throws Exception {
        Barcode bc1 = BarcodesApiControllerTestData.buildBarcode(11L,"SCGC","ABC",11L);
        Barcode bc2 = BarcodesApiControllerTestData.buildBarcode(122L,"SCGD","DEF",23l);

        String responseJson = BarcodesApiControllerTestData.buildResponseJson(
                Arrays.asList(bc1, bc2));

        given(barcodeRepositoryMock.findAll()).willReturn(Arrays.asList(bc1, bc2));

        mockMvc.perform(get("/barcodes/"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    @Test
    public void createBarcodeWithNotValidShortPrefix() throws Exception {
        final String prefix = "a";
        Barcode bc = BarcodesApiControllerTestData.buildBarcode(null, prefix, "XXX", null);
        Error expectedError = new Error()
                .code(HttpStatus.BAD_REQUEST.value())
                .fields("prefix")
                .message(BarcodeCreationValidator.INVALID_BARCODE_PREFIX_ERROR_MESSAGE);

        String responseJson = BarcodesApiControllerTestData.buildErrorResponseJson(
                expectedError);

        
        mockMvc.perform(post("/barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(bc))
                )
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    @Test
    public void createBarcodeWithNotAlphanumericPrefix() throws Exception {
        final String prefix = "?£%&";
        Barcode bc = BarcodesApiControllerTestData.buildBarcode(null, prefix, "XXX", null);
        Error expectedError = new Error()
                .code(HttpStatus.BAD_REQUEST.value())
                .fields("prefix")
                .message(BarcodeCreationValidator.INVALID_BARCODE_PREFIX_ERROR_MESSAGE);

        String responseJson = BarcodesApiControllerTestData.buildErrorResponseJson(
                expectedError);

        
        mockMvc.perform(post("/barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(bc))
                )
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    @Test
    public void createBarcodeWithNotValidLongInfo() throws Exception {
        final String info = "abcdefghij";
        final String prefix = "SCGC";
        Barcode bc = BarcodesApiControllerTestData.buildBarcode(null, prefix, info, null);
        Error expectedError = new Error()
                .code(HttpStatus.BAD_REQUEST.value())
                .fields("info")
                .message(BarcodeCreationValidator.INVALID_BARCODE_INFO_ERROR_MESSAGE);

        String responseJson = BarcodesApiControllerTestData.buildErrorResponseJson(
                expectedError);

        
        mockMvc.perform(post("/barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(bc))
                )
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    @Test
    public void createBarcodeWithNotAlphanumericInfo() throws Exception {
        final String info = "?£%";
        final String prefix = "SCGC";
        Barcode bc = BarcodesApiControllerTestData.buildBarcode(null, prefix, info, null);
        Error expectedError = new Error()
                .code(HttpStatus.BAD_REQUEST.value())
                .fields("info")
                .message(BarcodeCreationValidator.INVALID_BARCODE_INFO_ERROR_MESSAGE);

        String responseJson = BarcodesApiControllerTestData.buildErrorResponseJson(
                expectedError);

        mockMvc.perform(post("/barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(bc))
                )
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    @Test
    public void createBarcodeWithExistingPrefix () throws Exception {
        final String prefix = "SCGC";
        Barcode bc1Old = BarcodesApiControllerTestData.buildBarcode(99L, prefix, "XXX", 13L);
        Barcode bc1New = BarcodesApiControllerTestData.buildBarcode(99L, prefix, "XXX", 14L);

        String responseJson = BarcodesApiControllerTestData.buildResponseJson(
                Arrays.asList(bc1New));

        given(barcodeRepositoryMock.findByPrefix(prefix)).willReturn(bc1Old);
        given(barcodeRepositoryMock.save(bc1Old)).willReturn(bc1New);

        mockMvc.perform(post("/barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(bc1Old))
                )
        .andExpect(status().isCreated())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    @Test
    public void createBarcodeWithNonExistingPrefix () throws Exception {
        final String prefix = "SCGC";
        Barcode bc = BarcodesApiControllerTestData.buildBarcode(null, prefix, "XXX", null);
        Barcode bcCreated = BarcodesApiControllerTestData.buildBarcode(1L, prefix, "XXX", 1L);

        String responseJson = BarcodesApiControllerTestData.buildResponseJson(
                Arrays.asList(bcCreated));

        given(barcodeRepositoryMock.findByPrefix(prefix)).willReturn(null);
        given(barcodeRepositoryMock.save(bc)).willReturn(bcCreated);
        
        mockMvc.perform(post("/barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(bc))
                )
        .andExpect(status().isCreated())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }
}
