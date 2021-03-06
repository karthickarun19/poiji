package com.poiji.bind.mapping;

import com.poiji.bind.PoijiFile;
import com.poiji.exception.PoijiException;
import com.poiji.option.PoijiOptions;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

/**
 * Created by hakan on 22/10/2017
 */
final class XSSFUnmarshallerFile extends XSSFUnmarshaller {

    private final PoijiFile poijiFile;

    XSSFUnmarshallerFile(PoijiFile poijiFile, PoijiOptions options) {
        super(options);
        this.poijiFile = poijiFile;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> unmarshal(Class<T> type) {
        try (OPCPackage open = OPCPackage.open(poijiFile.file())) {

            return unmarshal0(type, open);

        } catch (SAXException | IOException | OpenXML4JException e) {
            throw new PoijiException("Problem occurred while reading data", e);
        }
    }
}
