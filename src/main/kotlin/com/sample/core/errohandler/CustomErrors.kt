package com.sample.core.errohandler

class AuthenticationException(message: String?) : RuntimeException(message)

class ConflictException(message: String?) : RuntimeException(message)

class AuthorizationException(message: String?) : RuntimeException(message)

class BadRequestException(message: String?) : RuntimeException(message)

class NotFoundException(message: String?) : RuntimeException(message)

class SomethingWentWrongException(message: String?) : RuntimeException(message)

class MissingRequestBodyException(message: String?) : RuntimeException(message)