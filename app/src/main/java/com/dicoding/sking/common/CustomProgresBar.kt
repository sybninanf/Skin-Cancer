import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProgressBar(progress: Int, modifier: Modifier = Modifier) {
    androidx.compose.foundation.Canvas(
        modifier = modifier,
        onDraw = {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val strokeWidth = 12f
            val startAngle = -90f
            val sweepAngle = (progress / 100f) * 360

            drawArc(
                color = Color(0xFF353438),
                startAngle = startAngle,
                sweepAngle = 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWidth)
            )

            drawArc(
                color = Color(0xFFFFCDD3),
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(strokeWidth)
            )
        }
    )
}
