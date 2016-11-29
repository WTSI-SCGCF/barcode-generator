/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BarcodesPayload;

/**
 * @author ke4
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BatchBarcodesApiController.class)
public class BatchBarcodesApiControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BarcodeRepository barcodeRepositoryMock;

    @Autowired
    ObjectMapper objectMapper;

    private List<Barcode> barcodesByPrefix = new ArrayList<>();

    @Test
    public void createBarcodeWhenPrefixNotExist() throws Exception {
        int numberOfBarcodes = 4;
        String prefix = "SCGC";
        String info = "ABC";
        Barcode bc1 = BarcodesApiControllerTestData.buildBarcode(null, prefix, info, 1L);
        Barcode bc2 = BarcodesApiControllerTestData.buildBarcode(11L, prefix, info, 2L);
        Barcode bc3 = BarcodesApiControllerTestData.buildBarcode(11L, prefix, info, 3L);
        Barcode bc4 = BarcodesApiControllerTestData.buildBarcode(11L, prefix, info, 4L);
        Barcode bc1New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, info, 1L);
        Barcode bc2New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, info, 2L);
        Barcode bc3New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, info, 3L);
        Barcode bc4New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, info, 4L);
        barcodesByPrefix.add(null);
        barcodesByPrefix.add(bc1New);
        barcodesByPrefix.add(bc2New);
        barcodesByPrefix.add(bc3New);
        BarcodesPayload barcodesPayload = new BarcodesPayload();
        barcodesPayload.setPrefix(prefix);
        barcodesPayload.setInfo(info);
        barcodesPayload.setNumberOfBarcodes(numberOfBarcodes);

        String responseJson = BarcodesApiControllerTestData.buildResponseJson(
                Arrays.asList(bc1New, bc2New, bc3New, bc4New));

        when(barcodeRepositoryMock.findByPrefix(prefix)).thenAnswer(
                new Answer<Barcode>() {
                    @Override
                    public Barcode answer(InvocationOnMock invocation) {
                        return returnByFindByPrefix();
                    }
                }
        );
        given(barcodeRepositoryMock.save(bc1)).willReturn(bc1New);
        given(barcodeRepositoryMock.save(bc2)).willReturn(bc2New);
        given(barcodeRepositoryMock.save(bc3)).willReturn(bc3New);
        given(barcodeRepositoryMock.save(bc4)).willReturn(bc4New);

        mockMvc.perform(post("/batch_barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(barcodesPayload))
                )
        .andExpect(status().isCreated())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    @Test
    public void createBarcodeWhenPrefixExist() throws Exception {
        int numberOfBarcodes = 4;
        String prefix = "SCGC";
        String info = "ABC";
        Barcode bc1Existing = BarcodesApiControllerTestData.buildBarcode(11L, prefix, "ABC", 11L);
        Barcode bc1New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, "ABC", 12L);
        Barcode bc2New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, "ABC", 13L);
        Barcode bc3New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, "ABC", 14L);
        Barcode bc4New = BarcodesApiControllerTestData.buildBarcode(11L, prefix, "ABC", 15L);
        BarcodesPayload barcodesPayload = new BarcodesPayload();
        barcodesPayload.setPrefix(prefix);
        barcodesPayload.setInfo(info);
        barcodesPayload.setNumberOfBarcodes(numberOfBarcodes);

        String responseJson = BarcodesApiControllerTestData.buildResponseJson(
                Arrays.asList(bc1New, bc2New, bc3New, bc4New));

        given(barcodeRepositoryMock.findByPrefix(prefix)).willReturn(bc1Existing);
        given(barcodeRepositoryMock.save(bc1Existing)).willReturn(bc1Existing);

        mockMvc.perform(post("/batch_barcodes/")
                .contentType(contentType)
                .content(objectMapper.writeValueAsBytes(barcodesPayload))
                )
        .andExpect(status().isCreated())
        .andExpect(content().contentType(contentType))
        .andDo(print())
        .andExpect(content().json(responseJson));
    }

    private Barcode returnByFindByPrefix() {
        return this.barcodesByPrefix.remove(0);
    }
}
