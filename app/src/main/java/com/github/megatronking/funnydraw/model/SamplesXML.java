package com.github.megatronking.funnydraw.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * XML beans.
 *
 * @author Magetron King
 * @since 18/7/27 00:10
 */
@XStreamAlias("samples")
public final class SamplesXML {

    @XStreamAsAttribute
    @XStreamAlias("package")
    public String packageName;

    @XStreamImplicit(itemFieldName = "sample")
    public List<SampleXML> samples;

}
