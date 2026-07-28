package com.cosmic.nova.data.repository

import com.cosmic.nova.data.model.Planet
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

interface SpaceRepository {
    suspend fun getPlanets(): List<Planet>
    suspend fun getPlanetById(id: String): Planet?
}

@Singleton
class SpaceRepositoryImpl @Inject constructor() : SpaceRepository {
    
    private val mockPlanets = listOf(
        Planet(
            id = "1",
            name = "Mars",
            type = "Terrestrial Planet",
            description = "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System, being larger than only Mercury. In English, Mars carries the name of the Roman god of war and is often referred to as the 'Red Planet'.",
            imageUrl = "https://images.unsplash.com/photo-1614728894747-a83421e2b9c9?q=80&w=1000&auto=format&fit=crop",
            temperature = "-65°C",
            mass = "6.39 × 10^23 kg",
            distance = "225 million km",
            colorHex = 0xFFFF5252
        ),
        Planet(
            id = "2",
            name = "Jupiter",
            type = "Gas Giant",
            description = "Jupiter is the fifth planet from the Sun and the largest in the Solar System. It is a gas giant with a mass more than two and a half times that of all the other planets in the Solar System combined, but slightly less than one-thousandth the mass of the Sun.",
            imageUrl = "https://images.unsplash.com/photo-1614314107768-6018061b5b72?q=80&w=1000&auto=format&fit=crop",
            temperature = "-110°C",
            mass = "1.898 × 10^27 kg",
            distance = "778 million km",
            colorHex = 0xFFFFD54F
        ),
        Planet(
            id = "3",
            name = "Saturn",
            type = "Gas Giant",
            description = "Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius of about nine and a half times that of Earth. It has only one-eighth the average density of Earth; however, with its larger volume, Saturn is over 95 times more massive.",
            imageUrl = "https://images.unsplash.com/photo-1614732414444-096e5f1122d5?q=80&w=1000&auto=format&fit=crop",
            temperature = "-140°C",
            mass = "5.683 × 10^26 kg",
            distance = "1.4 billion km",
            colorHex = 0xFF90CAF9
        ),
        Planet(
            id = "4",
            name = "Earth",
            type = "Terrestrial Planet",
            description = "Earth is the third planet from the Sun and the only astronomical object known to harbor life. While large amounts of water can be found throughout the Solar System, only Earth sustains liquid surface water. About 71% of Earth's surface is made up of the ocean, dwarfing Earth's polar ice, lakes, and rivers.",
            imageUrl = "https://images.unsplash.com/photo-1614730321146-b6fa6a46bcb4?q=80&w=1000&auto=format&fit=crop",
            temperature = "15°C",
            mass = "5.972 × 10^24 kg",
            distance = "0 km",
            colorHex = 0xFF4DB6AC
        )
    )

    override suspend fun getPlanets(): List<Planet> {
        delay(1000) // Simulate network delay
        return mockPlanets
    }

    override suspend fun getPlanetById(id: String): Planet? {
        return mockPlanets.find { it.id == id }
    }
}
