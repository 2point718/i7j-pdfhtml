/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2017 iText Group NV
    Authors: iText Software.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS

    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/

    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.

    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.

    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.

    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
 */
package com.itextpdf.html2pdf.css;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.LogMessageConstant;
import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ITagWorkerFactory;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.html2pdf.attach.impl.tags.PageMarginBoxWorker;
import com.itextpdf.html2pdf.css.apply.ICssApplier;
import com.itextpdf.html2pdf.css.apply.ICssApplierFactory;
import com.itextpdf.html2pdf.css.apply.impl.DefaultCssApplierFactory;
import com.itextpdf.html2pdf.css.apply.impl.PageMarginBoxCssApplier;
import com.itextpdf.html2pdf.css.page.PageMarginBoxContextNode;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.html2pdf.html.node.IStylesContainer;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.layout.element.Image;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.LogMessage;
import com.itextpdf.test.annotations.LogMessages;
import com.itextpdf.test.annotations.type.IntegrationTest;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class PageRuleTest extends ExtendedITextTest {
    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/PageRuleTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/css/PageRuleTest/";

    @BeforeClass
    public static void beforeClass() {
        createOrClearDestinationFolder(destinationFolder);
    }

    @Test
    public void marksCropCrossPageRuleTest() throws IOException, InterruptedException {
        runTest("marksCropCrossPageRuleTest");
    }

    @Test
    public void marksCropPageRuleTest() throws IOException, InterruptedException {
        runTest("marksCropPageRuleTest");
    }

    @Test
    public void marksCrossPageRuleTest() throws IOException, InterruptedException {
        runTest("marksCrossPageRuleTest");
    }

    @Test
    public void marksInvalidPageRuleTest() throws IOException, InterruptedException {
        runTest("marksInvalidPageRuleTest");
    }

    @Test
    public void marksNonePageRuleTest() throws IOException, InterruptedException {
        runTest("marksNonePageRuleTest");
    }

    @Test
    public void paddingPageRuleTest() throws IOException, InterruptedException {
        runTest("paddingPageRuleTest");
    }

    @Test
    public void compoundSizePageRuleTest() throws IOException, InterruptedException {
        runTest("compoundSizePageRuleTest");
    }

    @Test
    public void bleedPageRuleTest() throws IOException, InterruptedException {
        runTest("bleedPageRuleTest");
    }

    @Test
    @LogMessages(messages = @LogMessage(messageTemplate = LogMessageConstant.PAGE_SIZE_VALUE_IS_INVALID, count = 3))
    public void invalidCompoundSizePageRuleTest() throws IOException, InterruptedException {
        runTest("invalidCompoundSizePageRuleTest");
    }

    @Test
    public void notAllMarginsPageRuleTest() throws IOException, InterruptedException {
        runTest("notAllMarginsPageRuleTest");
    }

    @Test
    public void firstLeftRightPageRuleTest() throws IOException, InterruptedException {
        runTest("firstLeftRightPageRuleTest");
    }

    @Test
    public void marksBleedPageRuleTest() throws IOException, InterruptedException {
        runTest("marksBleedPageRuleTest");
    }

    @Test
    @LogMessages(messages = @LogMessage(messageTemplate = LogMessageConstant.CONTENT_PROPERTY_INVALID, count = 3))
    public void marginBoxTest01() throws IOException, InterruptedException {
        runTest("marginBoxTest01");
    }

    @Test
    public void marginBoxTest02() throws IOException, InterruptedException {
        runTest("marginBoxTest02");
    }

    @Test
    public void marginBoxTest03() throws IOException, InterruptedException {
        runTest("marginBoxTest03");
    }

    @Test
    public void marginBoxTest04() throws IOException, InterruptedException {
        runTest("marginBoxTest04");
    }

    @Test
    public void bigImageOnPageMarginTest01() throws IOException, InterruptedException {
        runTest("bigImageOnPageMarginTest01");
    }

    @Test
    public void bigImageOnPageMarginTest02() throws IOException, InterruptedException {
        runTest("bigImageOnPageMarginTest02");
    }

    @Test
    public void bigImageOnPageMarginTest03() throws IOException, InterruptedException {
        runTest("bigImageOnPageMarginTest03", new PageMarginBoxImagesTagWorkerFactory(), null);
    }

    private static class PageMarginBoxImagesTagWorkerFactory extends DefaultTagWorkerFactory {
        @Override
        public ITagWorker getCustomTagWorker(IElementNode tag, ProcessorContext context) {
            if (tag.name().equals(PageMarginBoxContextNode.PAGE_MARGIN_BOX_TAG)) {
                return new PageMarginBoxImagesWorker(tag, context);
            }
            return super.getCustomTagWorker(tag, context);
        }
    }

    private static class PageMarginBoxImagesWorker extends PageMarginBoxWorker {
        public PageMarginBoxImagesWorker(IElementNode element, ProcessorContext context) {
            super(element, context);
        }

        @Override
        public boolean processTagChild(ITagWorker childTagWorker, ProcessorContext context) {
            if (childTagWorker.getElementResult() instanceof Image) {
                // TODO Since iText 7.2 release it is ("it will be" for now, see PageMarginBoxDummyElement class) possible
                // to get current page margin box name and dimensions from the "element" IElementNode passed to the constructor of this tag worker.
                ((Image) childTagWorker.getElementResult()).setAutoScale(true);
            }
            return super.processTagChild(childTagWorker, context);
        }
    }

    @Test
    public void bigTextOnPageMarginTest01() throws IOException, InterruptedException {
        runTest("bigTextOnPageMarginTest01");
    }

    @Test
    public void bigTextOnPageMarginTest02() throws IOException, InterruptedException {
        runTest("bigTextOnPageMarginTest02");
    }

    @Test
    public void marginBoxOverflowPropertyTest01() throws IOException, InterruptedException {
        runTest("marginBoxOverflowPropertyTest01");
    }

    @Test
    public void marginBoxOverflowPropertyTest02() throws IOException, InterruptedException {
        runTest("marginBoxOverflowPropertyTest02", null, new PageMarginsOverflowCssApplierFactory());
    }

    private static class PageMarginsOverflowCssApplierFactory extends DefaultCssApplierFactory {
        @Override
        public ICssApplier getCustomCssApplier(IElementNode tag) {
            if (PageMarginBoxContextNode.PAGE_MARGIN_BOX_TAG.equals(tag.name())) {
                return new CustomOverflowPageMarginBoxCssApplier();
            }
            return super.getCustomCssApplier(tag);
        }
    }

    private static class CustomOverflowPageMarginBoxCssApplier extends PageMarginBoxCssApplier {
        @Override
        public void apply(ProcessorContext context, IStylesContainer stylesContainer, ITagWorker tagWorker) {
            Map<String, String> styles = stylesContainer.getStyles();
            if (styles.get(CssConstants.OVERFLOW) == null) {
                styles.put(CssConstants.OVERFLOW, CssConstants.VISIBLE);
            }
            super.apply(context, stylesContainer, tagWorker);
        }
    }

    @Test
    public void marginBoxOutlinePropertyTest01() throws IOException, InterruptedException {
        // TODO Outlines are currently not supported for page margin boxes, because of the outlines handling specificity (they are handled on renderer's parent level).
        //      See com.itextpdf.html2pdf.attach.impl.layout.PageContextProcessor.
        runTest("marginBoxOutlinePropertyTest01");
    }


    private void runTest(String name) throws IOException, InterruptedException {
        runTest(name, null, null);
    }

    private void runTest(String name, ITagWorkerFactory customTagWorkerFactory, ICssApplierFactory customCssApplierFactory) throws IOException, InterruptedException {
        String htmlPath = sourceFolder + name + ".html";
        String pdfPath = destinationFolder + name + ".pdf";
        String cmpPdfPath = sourceFolder + "cmp_" + name + ".pdf";
        String diffPrefix = "diff_" + name + "_";

        ConverterProperties converterProperties = new ConverterProperties();
        if (customTagWorkerFactory != null) {
            converterProperties.setTagWorkerFactory(customTagWorkerFactory);
        }
        if (customCssApplierFactory != null) {
            converterProperties.setCssApplierFactory(customCssApplierFactory);
        }

        HtmlConverter.convertToPdf(new File(htmlPath), new File(pdfPath), converterProperties);
        Assert.assertNull(new CompareTool().compareByContent(pdfPath, cmpPdfPath, destinationFolder, diffPrefix));
    }
}
