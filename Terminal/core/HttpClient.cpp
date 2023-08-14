#include "HttpClient.h"
#include <curl/curl.h>
#include <string>
#include <iostream>

HttpClient::HttpClient()
{
    curl_global_init(CURL_GLOBAL_DEFAULT);
}

void HttpClient::create_curl_handle() {
    this->curl = curl_easy_init();
}

HttpResponse HttpClient::fetch(std::string &url) {
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());

    std::string body;
    long statusCode;

    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &body);

    CURLcode res = curl_easy_perform(curl);
    curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &statusCode);

    if (res != CURLE_OK) {
        std::cerr << "Failed to perform request: " << curl_easy_strerror(res) << std::endl;
        return HttpResponse {-1, "CURL_FAIL"};
    } else {

        std::cout << "Fetched content:" << std::endl;
        std::cout << body << std::endl;
        return HttpResponse {statusCode, body};
    }
}

size_t HttpClient::WriteCallback(void* contents, size_t size, size_t nmemb, void* userptr) {
    ((std::string*)userptr)->append((char*)contents, size * nmemb);
    return size * nmemb;
}

HttpClient::~HttpClient()
{
    curl_global_cleanup();
}




