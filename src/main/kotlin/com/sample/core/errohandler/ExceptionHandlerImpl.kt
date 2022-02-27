package com.sample.core.errohandler

class ExceptionHandlerImpl : ExceptionHandler {

    override fun respondGenericException(message: String): Exception {
        return SomethingWentWrongException(message)
    }

    override fun respondNotFoundException(message: String): Exception {
        return NotFoundException(message)
    }

    override fun respondBadRequestException(message: String): Exception {
        return BadRequestException(message)
    }

    override fun respondUnAuthorizedException(message: String): Exception {
        return AuthorizationException(message)
    }

    override fun respondAlreadyExistException(message: String): Exception {
        return ConflictException(message)
    }
}
