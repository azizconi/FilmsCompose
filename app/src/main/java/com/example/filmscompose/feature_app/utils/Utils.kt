package com.example.filmscompose.feature_app.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.BlurMaskFilter
import android.util.DisplayMetrics
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T> safeApiCall(call: suspend() -> Response<T>): Flow<Resource<T>> = flow {
    emit(Resource.Loading())

    var remoteData: Response<T>? = null
    try {
        remoteData = call()
        val data = remoteData.body()
        emit(Resource.Success(data))
    } catch (e: HttpException) {
        emit(
            Resource.Error(
                message = Constants.HttpExceptionError,
                data = null
            )
        )
    } catch (e: IOException) {
        emit(
            Resource.Error(
                message = Constants.IOExceptionError,
                data = null
            )
        )
    }
}




fun Context.percentageWidthSize(percentage: Int): Dp {
    val displayMetrics: DisplayMetrics = resources.displayMetrics
    return ((displayMetrics.widthPixels * percentage) / 100).toDp()/*((percentage * displayMetrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT).dp*/
}

fun Int.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}

fun percentageSize(parentSize: Dp, percentage: Int) = (parentSize * percentage) / 100