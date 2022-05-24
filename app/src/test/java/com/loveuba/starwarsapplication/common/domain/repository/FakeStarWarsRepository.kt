package com.loveuba.starwarsapplication.common.domain.repository

import com.loveuba.starwarsapplication.data.models.CharacterData
import com.loveuba.starwarsapplication.data.models.FilmData
import com.loveuba.starwarsapplication.data.models.PlanetData
import com.loveuba.starwarsapplication.data.models.SpeciesData
import com.loveuba.starwarsapplication.data.repository.IStarwarsRepository
import com.loveuba.starwarsapplication.data.wrapper.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeStarWarsRepository : IStarwarsRepository {
    override suspend fun fetchSearchCharacters(query: String): Flow<Result<List<CharacterData>>> {
        val characterTestData = listOf(
            CharacterData(
                "896BBY",
                "2014-12-15T12:26:01.042000Z",
                "2014-12-20T21:17:50.345000Z",
                "brown",
                listOf(
                    "https://swapi.dev/api/films/2/",
                    "https://swapi.dev/api/films/3/",
                    "https://swapi.dev/api/films/4/",
                    "https://swapi.dev/api/films/5/",
                    "https://swapi.dev/api/films/6/"
                ),
                "male",
                "white",
                "66",
                "https://swapi.dev/api/planets/28/",
                "17",
                "Yoda",
                "green",
                listOf("https://swapi.dev/api/species/6/"),
                listOf(""),
                "https://swapi.dev/api/people/20/",
                listOf("")
            ),
            CharacterData(
                "72BBY",
                "2014-12-19T17:57:41.191000Z",
                "2014-12-20T21:17:50.401000Z",
                "brown",
                listOf(
                    "https://swapi.dev/api/films/4/",
                    "https://swapi.dev/api/films/5/"
                ),
                "female",
                "black",
                "163",
                "https://swapi.dev/api/planets/1/",
                "unknown",
                "Shmi Skywalker",
                "fair",
                listOf(""),
                listOf(""),
                "https://swapi.dev/api/people/43/",
                listOf("")
            )
        )

        return flowOf(Result.Success(characterTestData))
    }

    override suspend fun fetchCharacterPlanet(planetPath: String): Flow<Result<PlanetData>> {

        val planetTestData = PlanetData(
            "temperate",
            "2014-12-10T11:52:31.066000Z",
            "12120",
            "2014-12-20T20:58:18.430000Z",
            listOf(
                "https://swapi.dev/api/films/3/",
                "https://swapi.dev/api/films/4/",
                "https://swapi.dev/api/films/5/",
                "https://swapi.dev/api/films/6/"
            ),
            "1 standard",
            "Naboo",
            "312",
            "4500000000",
            listOf(
                "https://swapi.dev/api/people/3/",
                "https://swapi.dev/api/people/21/",
                "https://swapi.dev/api/people/35/",
                "https://swapi.dev/api/people/36/",
                "https://swapi.dev/api/people/37/",
                "https://swapi.dev/api/people/38/",
                "https://swapi.dev/api/people/39/",
                "https://swapi.dev/api/people/42/",
                "https://swapi.dev/api/people/60/",
                "https://swapi.dev/api/people/61/",
                "https://swapi.dev/api/people/66/"
            ),
            "26",
            "12",
            "grassy hills, swamps, forests, mountains",
            "https://swapi.dev/api/planets/8/"
        )
        return flowOf(Result.Success(planetTestData))
    }


    override suspend fun fetchCharacterSpecies(speciesPath: String): Flow<Result<SpeciesData>> {
        val speciesTestData = SpeciesData(
            "180",
            "unknown",
            "mammal",
            "2014-12-20T10:26:59.894000Z",
            "sentient",
            "2014-12-20T21:36:42.183000Z",
            "brown, orange",
            listOf(
                "https://swapi.dev/api/films/4/"
            ),
            "black",
            "https://swapi.dev/api/planets/45/",
            "Zabraki",
            "Zabrak",
            listOf(
                "https://swapi.dev/api/people/44/",
                "https://swapi.dev/api/people/54/"
            ),
            "pale, brown, red, orange, yellow",
            "https://swapi.dev/api/species/22/"
        )
        return flowOf(Result.Success(speciesTestData))
    }

    override suspend fun fetchAllFilms(): Flow<Result<List<FilmData>>> {
        val filmTestList = listOf(
            FilmData(
                listOf(
                    "https://swapi.dev/api/people/1/",
                    "https://swapi.dev/api/people/2/",
                    "https://swapi.dev/api/people/3/",
                    "https://swapi.dev/api/people/4/",
                    "https://swapi.dev/api/people/5/",
                    "https://swapi.dev/api/people/6/",
                    "https://swapi.dev/api/people/7/",
                    "https://swapi.dev/api/people/8/",
                    "https://swapi.dev/api/people/9/",
                    "https://swapi.dev/api/people/10/",
                    "https://swapi.dev/api/people/12/",
                    "https://swapi.dev/api/people/13/",
                    "https://swapi.dev/api/people/14/",
                    "https://swapi.dev/api/people/15/",
                    "https://swapi.dev/api/people/16/",
                    "https://swapi.dev/api/people/18/",
                    "https://swapi.dev/api/people/19/",
                    "https://swapi.dev/api/people/81/"
                ),
                "2014-12-10T14:23:31.880000Z",
                "George Lucas",
                "2014-12-20T19:49:45.256000Z",
                4,
                "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
                listOf(
                    "https://swapi.dev/api/planets/1/",
                    "https://swapi.dev/api/planets/2/",
                    "https://swapi.dev/api/planets/3/"
                ),
                "Gary Kurtz, Rick McCallum",
                "1977-05-25",
                listOf(
                    "https://swapi.dev/api/species/1/",
                    "https://swapi.dev/api/species/2/",
                    "https://swapi.dev/api/species/3/",
                    "https://swapi.dev/api/species/4/",
                    "https://swapi.dev/api/species/5/"
                ),
                listOf(
                    "https://swapi.dev/api/starships/2/",
                    "https://swapi.dev/api/starships/3/",
                    "https://swapi.dev/api/starships/5/",
                    "https://swapi.dev/api/starships/9/",
                    "https://swapi.dev/api/starships/10/",
                    "https://swapi.dev/api/starships/11/",
                    "https://swapi.dev/api/starships/12/",
                    "https://swapi.dev/api/starships/13/"
                ),
                "A New Hope",
                "https://swapi.dev/api/films/1/",
                listOf(
                    "https://swapi.dev/api/vehicles/4/",
                    "https://swapi.dev/api/vehicles/6/",
                    "https://swapi.dev/api/vehicles/7/",
                    "https://swapi.dev/api/vehicles/8/"
                )
            ),
            FilmData(
                listOf(
                    "https://swapi.dev/api/people/1/",
                    "https://swapi.dev/api/people/2/",
                    "https://swapi.dev/api/people/3/",
                    "https://swapi.dev/api/people/4/",
                    "https://swapi.dev/api/people/5/",
                    "https://swapi.dev/api/people/6/",
                    "https://swapi.dev/api/people/7/",
                    "https://swapi.dev/api/people/8/",
                    "https://swapi.dev/api/people/9/",
                    "https://swapi.dev/api/people/10/",
                    "https://swapi.dev/api/people/12/",
                    "https://swapi.dev/api/people/13/",
                    "https://swapi.dev/api/people/14/",
                    "https://swapi.dev/api/people/15/",
                    "https://swapi.dev/api/people/16/",
                    "https://swapi.dev/api/people/18/",
                    "https://swapi.dev/api/people/19/",
                    "https://swapi.dev/api/people/81/"
                ),
                "2014-12-10T14:23:31.880000Z",
                "George Lucas",
                "2014-12-20T19:49:45.256000Z",
                4,
                "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
                listOf(
                    "https://swapi.dev/api/planets/1/",
                    "https://swapi.dev/api/planets/2/",
                    "https://swapi.dev/api/planets/3/"
                ),
                "Gary Kurtz, Rick McCallum",
                "1977-05-25",
                listOf(
                    "https://swapi.dev/api/species/1/",
                    "https://swapi.dev/api/species/2/",
                    "https://swapi.dev/api/species/3/",
                    "https://swapi.dev/api/species/4/",
                    "https://swapi.dev/api/species/5/"
                ),
                listOf(
                    "https://swapi.dev/api/starships/2/",
                    "https://swapi.dev/api/starships/3/",
                    "https://swapi.dev/api/starships/5/",
                    "https://swapi.dev/api/starships/9/",
                    "https://swapi.dev/api/starships/10/",
                    "https://swapi.dev/api/starships/11/",
                    "https://swapi.dev/api/starships/12/",
                    "https://swapi.dev/api/starships/13/"
                ),
                "Jungle of Joy",
                "https://swapi.dev/api/films/1/",
                listOf(
                    "https://swapi.dev/api/vehicles/4/",
                    "https://swapi.dev/api/vehicles/6/",
                    "https://swapi.dev/api/vehicles/7/",
                    "https://swapi.dev/api/vehicles/8/"
                )
            ),
            FilmData(
                listOf(
                    "https://swapi.dev/api/people/1/",
                    "https://swapi.dev/api/people/2/",
                    "https://swapi.dev/api/people/3/",
                    "https://swapi.dev/api/people/4/",
                    "https://swapi.dev/api/people/5/",
                    "https://swapi.dev/api/people/6/",
                    "https://swapi.dev/api/people/7/",
                    "https://swapi.dev/api/people/8/",
                    "https://swapi.dev/api/people/9/",
                    "https://swapi.dev/api/people/10/",
                    "https://swapi.dev/api/people/12/",
                    "https://swapi.dev/api/people/13/",
                    "https://swapi.dev/api/people/14/",
                    "https://swapi.dev/api/people/15/",
                    "https://swapi.dev/api/people/16/",
                    "https://swapi.dev/api/people/18/",
                    "https://swapi.dev/api/people/19/",
                    "https://swapi.dev/api/people/81/"
                ),
                "2014-12-10T14:23:31.880000Z",
                "George Lucas",
                "2014-12-20T19:49:45.256000Z",
                4,
                "It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
                listOf(
                    "https://swapi.dev/api/planets/1/",
                    "https://swapi.dev/api/planets/2/",
                    "https://swapi.dev/api/planets/3/"
                ),
                "Gary Kurtz, Rick McCallum",
                "1977-05-25",
                listOf(
                    "https://swapi.dev/api/species/1/",
                    "https://swapi.dev/api/species/2/",
                    "https://swapi.dev/api/species/3/",
                    "https://swapi.dev/api/species/4/",
                    "https://swapi.dev/api/species/5/"
                ),
                listOf(
                    "https://swapi.dev/api/starships/2/",
                    "https://swapi.dev/api/starships/3/",
                    "https://swapi.dev/api/starships/5/",
                    "https://swapi.dev/api/starships/9/",
                    "https://swapi.dev/api/starships/10/",
                    "https://swapi.dev/api/starships/11/",
                    "https://swapi.dev/api/starships/12/",
                    "https://swapi.dev/api/starships/13/"
                ),
                "Yesterday",
                "https://swapi.dev/api/films/1/",
                listOf(
                    "https://swapi.dev/api/vehicles/4/",
                    "https://swapi.dev/api/vehicles/6/",
                    "https://swapi.dev/api/vehicles/7/",
                    "https://swapi.dev/api/vehicles/8/"
                )
            )
        )
        return flowOf(Result.Success(filmTestList))
    }
}