package com.nehal.quotehouse.network

class ApiMainHeadersProvider {

    /**
     * Public headers for calls that do not need an authenticated user.
     */
    fun getPublicHeaders(): PublicHeaders =
        PublicHeaders().apply {
            putAll(getDefaultHeaders())
        }

    /**
     * Returns both the default headers and the headers that are mandatory for authenticated users.
     */
    fun getAuthenticatedHeaders(accessToken: AccessToken): AuthenticatedHeaders =
        AuthenticatedHeaders().apply {
            putAll(getDefaultHeaders())
            put(AUTHORIZATION, getBearer(accessToken))
        }

    /**
     * Default headers used on all calls.
     */
    private fun getDefaultHeaders() = mapOf(
        XParseApplicationId to XParseApplicationIdValue,
        HEADER_ACCEPT to "application/json",
        XParseRESTAPIKey to XParseRESTAPIKeyValue
    )

    companion object {
        private const val ACCEPT_LANGUAGE = "Accept-Language"
        private const val USER_AGENT = "User-Agent"
        private const val AUTHORIZATION = "Authorization"
        private const val HEADER_ACCEPT = "Accept"

        private const val XParseApplicationId = "X-Parse-Application-Id"
        private const val XParseRESTAPIKey = "X-Parse-REST-API-Key"

        private const val XParseApplicationIdValue = "JcABJkhzoG2kLuTiA0Ief4EvoZLreP0TbYFruLmx"
        private const val XParseRESTAPIKeyValue = "RNy26FjfY4IilAr84MhfdsRMuTwAdA5Lh4OqYNDs"

        private fun getBearer(accessToken: AccessToken) = "Bearer ${accessToken.value}"
    }
}

open class ApiMainHeaders : HashMap<String, String>()
class AuthenticatedHeaders : ApiMainHeaders()
class PublicHeaders : ApiMainHeaders()
