package org.broadinstitute.hellbender.cmdline.argumentcollections;

import org.broadinstitute.barclay.argparser.Argument;
import org.broadinstitute.hellbender.cmdline.StandardArgumentDefinitions;
import org.broadinstitute.hellbender.engine.GATKPathSpecifier;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * An argument collection for use with tools that accept zero or more input files containing reads
 * (eg., BAM/SAM/CRAM files).
 */
public final class OptionalReadInputArgumentCollection extends ReadInputArgumentCollection {
    private static final long serialVersionUID = 1L;

    @Argument(fullName = StandardArgumentDefinitions.INPUT_LONG_NAME,
            shortName = StandardArgumentDefinitions.INPUT_SHORT_NAME,
            doc = "BAM/SAM/CRAM file containing reads",
            optional = true,
            common = true)
    private List<GATKPathSpecifier> readInputNames = new ArrayList<>();

    @Override
    public List<Path> getReadPaths() {
        ArrayList<Path> ret = new ArrayList<>();
        for (GATKPathSpecifier fn : readInputNames) {
            ret.add(fn.toPath());
        }
        return ret;
    }

    /**
     * Temporary staging method for backward compatibility with all of the existing call sites that
     * expect a raw file name with no protocol scheme."
     */
    // TODO: When all of he call sites have been updated make this go away....
    @Override
    public List<String> getRawInputStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (GATKPathSpecifier fn : readInputNames) {
            ret.add(fn.getRawInputString());
        }
        return ret;
    }

}
