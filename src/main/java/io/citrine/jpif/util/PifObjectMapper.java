package io.citrine.jpif.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.citrine.jpif.obj.system.System;

import java.util.List;

/**
 * Class used to serialize and deserialize from the PIF schema.
 *
 * @author Kyle Michel
 */
public class PifObjectMapper extends ObjectMapper {

    /**
     * Get a static instance of this class.
     *
     * @return {@link PifObjectMapper} object.
     */
    public static PifObjectMapper getInstance() {
        return Holder.PIF_OBJECT_MAPPER;
    }

    /**
     * Get a writer for writing lists of systems.
     *
     * @return {@link ObjectWriter} for a list of {@link System} objects.
     */
    public ObjectWriter getSystemListWriter() {
        return this.writerFor(Holder.SYSTEM_LIST_TYPE);
    }

    /**
     * Constructor. This configures the object mapper underlying this class.
     */
    private PifObjectMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        this.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        this.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
    }

    /**
     * Holder class.
     *
     * @author Kyle Michel
     */
    private static class Holder {

        /** Instance of the PIF object mapper to use. */
        private static final PifObjectMapper PIF_OBJECT_MAPPER = new PifObjectMapper();

        /** Type for a list of systems. */
        private static final TypeReference<List<System>> SYSTEM_LIST_TYPE = new TypeReference<List<System>>() {};
    }
}