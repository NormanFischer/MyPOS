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

std::string HttpClient::fetch(std::string &url) {
    std::cout << "Fetch requested!" << std::endl;
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());

    std::string response;
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &response);

    CURLcode res = curl_easy_perform(curl);
    if (res != CURLE_OK) {
        std::cerr << "Failed to perform request: " << curl_easy_strerror(res) << std::endl;
        return "";
    } else {
        std::cout << "Fetched content:" << std::endl;
        std::cout << response << std::endl;
        return response;
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




