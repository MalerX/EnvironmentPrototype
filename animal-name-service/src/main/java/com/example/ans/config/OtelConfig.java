package com.example.ans.config;

import io.grpc.ManagedChannelBuilder;
import io.opentelemetry.OpenTelemetry;
import io.opentelemetry.exporters.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.trace.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtelConfig {
    private static final String traceName = "nameGenTrace";
    private String jaegerAddress;

    @Bean
    public Tracer otelTracer() {
        final Tracer tracer = OpenTelemetry.getTracer(traceName);

        SpanProcessor jaegerProcessor = SimpleSpanProcessor
                .newBuilder(
                        JaegerGrpcSpanExporter
                                .newBuilder()
                                .setChannel(
                                        ManagedChannelBuilder.forAddress("jaeger", 14250).usePlaintext().build()
                                )
                                .setServiceName(traceName)
                                .build()
                )
                .build();
        OpenTelemetrySdk.getTracerProvider().addSpanProcessor(jaegerProcessor);
        return tracer;
    }
}
