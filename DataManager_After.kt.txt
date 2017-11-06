class DataManagerTest {

    @Rule @JvmField val overrideSchedulersRule = RxSchedulersOverrideRule()

    @Mock lateinit var mockPokemonService: PokemonService
    private lateinit var dataManager: DataManager

    @Before
    fun setUp() {
        dataManager = DataManager(mockPokemonService)
    }
    
    @Test
    fun getPokemonCompletesAndEmitsPokemon() {
        val name = "charmander"
        val pokemon = TestDataFactory.makePokemon(name)
        `when`(mockPokemonService.getPokemon(anyString())).thenReturn(Single.just(pokemon))

        dataManager.getPokemon(name).test().assertComplete().assertValue(pokemon)
    }
}