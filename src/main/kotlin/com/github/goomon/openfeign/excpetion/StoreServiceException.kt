package com.github.goomon.openfeign.excpetion

sealed class StoreServiceException : RuntimeException()

class StoreServerException : StoreServiceException()

class StoreClientException : StoreServiceException()
