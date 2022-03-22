package com.humlib.config

import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder
import org.apache.http.message.BasicHeader
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

@Configuration
class RestClientConfig(private val config: ElasticsearchConfig) : AbstractElasticsearchConfiguration() {

    @Bean
    override fun elasticsearchClient(): RestHighLevelClient {
        val credentialsProvider = BasicCredentialsProvider()
        credentialsProvider.setCredentials(AuthScope.ANY, UsernamePasswordCredentials(config.username, config.password))

        var context: SSLContext? = null

        // CREATE A KEYSTORE OF TYPE "pkcs12"
        val ks = KeyStore.getInstance("pkcs12")

        /*
         * LOAD THE STORE
         * The first time you're doing this (i.e. the keystore does not
         * yet exist - you're creating it), you HAVE to load the keystore
         * from a null source with null password. Before any methods can
         * be called on your keystore you HAVE to load it first. Loading
         * it from a null source and null password simply creates an empty
         * keystore. At a later time, when you want to verify the keystore
         * or get certificates (or whatever) you can load it from the
         * file with your password.
         */
        ks.load(null, null)

        // GET THE FILE CONTAINING YOUR CERTIFICATE
        val fis = FileInputStream(System.getenv("HUMLIB_BE_DOCKER_HOME") + "/elasticstack/volumes/certs/ca/ca.crt")
        val bis = BufferedInputStream(fis)

        // .crt is an extension of X.509 type
        val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
        val cert = cf.generateCertificate(bis)

        //ADD TO THE KEYSTORE AND GIVE IT AN ALIAS NAME
        ks.setCertificateEntry("ca", cert)

        // INIT A TRUST MANAGER FACTORY
        val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        tmf.init(ks)

        // INIT A SSLCONTEXT
        context = SSLContext.getInstance("TLS")
        context.init(null, tmf.trustManagers, null)

        // BUILD A REST CLIENT WITH THE CREDENTIALS PROVIDER AND THE SSL CONTEXT INITIALIZED PREVIOUSLY
        val restClientBuilder = RestClient.builder(
            HttpHost(config.host, config.port, config.scheme)
        ).setHttpClientConfigCallback { httpClientBuilder: HttpAsyncClientBuilder ->
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)
            httpClientBuilder.setDefaultHeaders(
                mutableListOf(
                    BasicHeader("Accept", "application/vnd.elasticsearch+json;compatible-with=7"), BasicHeader(
                        "Content-Type", "application/vnd.elasticsearch+json;"
                                + "compatible-with=7"
                    )
                )
            )
            httpClientBuilder.setSSLContext(context)
        }
        return RestHighLevelClient(restClientBuilder)
    }
}