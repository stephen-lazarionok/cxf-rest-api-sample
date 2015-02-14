package com.sla.sampleapp.infrastructure.shared;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Stephen Lazarionok.
 * Extends the Jackson JSON provider to consume "text/javascript".
 */
@Provider
@Consumes("text/javascript")
public class JsTextProvider extends JacksonJsonProvider {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {

        return isTextJavascript(mediaType) ||
                super.isReadable(type, genericType, annotations, mediaType);
    }

    private boolean isTextJavascript(final MediaType mediaType) {

        return mediaType != null
                && "text".equalsIgnoreCase(mediaType.getType())
                && "javascript".equalsIgnoreCase(mediaType.getSubtype());
    }
}
