package com.github.megatronking.funnydraw.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * XML beans.
 *
 * @author Magetron King
 * @since 18/7/27 00:10
 */
@XStreamAlias("sample")
public final class SampleXML {

    @XStreamAsAttribute
    @XStreamAlias("class")
    public String className;

    @XStreamAsAttribute
    @XStreamAlias("name")
    public String name;

}
