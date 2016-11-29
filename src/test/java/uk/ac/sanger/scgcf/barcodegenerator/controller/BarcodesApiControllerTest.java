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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;

/**
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
