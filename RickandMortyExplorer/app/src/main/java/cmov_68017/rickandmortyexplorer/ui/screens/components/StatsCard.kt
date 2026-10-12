package cmov_68017.rickandmortyexplorer.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cmov_68017.rickandmortyexplorer.ui.viewmodel.Category

@Composable
fun StatsCard(
    category: Category,
    totalCount: Int,
    currentViewCount: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "API Stats",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            val categoryName = category.name.lowercase().replaceFirstChar { it.uppercase() }
            Text(
                text = "Total ${categoryName}s in API: $totalCount",
                fontSize = 14.sp
            )
            Text(
                text = "Retrieved in current view: $currentViewCount items",
                fontSize = 14.sp
            )
        }
    }
}