#include "HttpClient.h"
#include <curl/curl.h>
#include <string>
#include <iostream>

HttpClient::HttpClient(const std::string url)
{
    this->url = url;
    curl_global_init(CURL_GLOBAL_DEFAULT);
}

void HttpClient::create_curl_handle() {
    this->curl = curl_easy_init();
}

//How we recieve session id
std::string HttpClient::login(const std::string username, const std::string password) {
    std::string header_data;
    curl_easy_setopt(curl, CURLOPT_URL, (url + "/login").c_str());
    curl_easy_setopt(curl, CURLOPT_POST, 1);
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, "username="+username+"&password="+password);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_HEADERFUNCTION, header_callback);
    curl_easy_setopt(curl, CURLOPT_HEADERDATA, &header_data);
    //We only need the session id which is contained in a header
    long statusCode;

    CURLcode res = curl_easy_perform(curl);
    curl_easy_getinfo(curl, CURLINFO_RESPONSE_CODE, &statusCode);

    if (res != CURLE_OK) {
        std::cerr << "Failed to perform request: " << curl_easy_strerror(res) << std::endl;
        return "";
    } else {
        std::cout << "Logged in!" << std::endl;
        //Get session id
        std::cout << header_data << std::endl;
        std::string cookie_name = "Set-Cookie:";
        size_t start_pos = header_data.find(cookie_name);
        start_pos = start_pos + cookie_name.length();
        size_t end_pos = start_pos;
        while(header_data[end_pos] != ';') {
            std::cout << header_data[end_pos] << std::endl;
            end_pos++;
        }
        std::cout << header_data.substr(start_pos, end_pos - start_pos) << std::endl;
        return header_data.substr(start_pos, end_pos - start_pos);
    }
}

//HTTP get params should be provided in endpoint
//Do NOT use this to login! Headers will NOT be parsed!
HttpResponse HttpClient::fetch(const std::string &endpoint, HTTP_METHOD method, std::string jsonBody) {
    curl_easy_setopt(curl, CURLOPT_URL, (url + endpoint).c_str());

    switch(method) {
        case GET:
            break;
        case POST:
            curl_easy_setopt(curl, CURLOPT_POST, 1);
            curl_easy_setopt(curl, CURLOPT_POSTFIELDS, jsonBody);
            break;
        default:
            std::cout << "Error: Invalid HTTP Method" << std::endl;
            return HttpResponse {-1, "INVALID_METHOD"};
    }

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

size_t HttpClient::header_callback(char *buffer, size_t size, size_t nitems, void *userdata)
{
    size_t total_size = size * nitems;
    std::string& res = *static_cast<std::string*>(userdata);
    res.append(buffer, total_size);

    return total_size;
}

size_t HttpClient::WriteCallback(void* contents, size_t size, size_t nmemb, void* userptr) {
    ((std::string*)userptr)->append((char*)contents, size * nmemb);
    return size * nmemb;
}

HttpClient::~HttpClient()
{
    curl_global_cleanup();
}




