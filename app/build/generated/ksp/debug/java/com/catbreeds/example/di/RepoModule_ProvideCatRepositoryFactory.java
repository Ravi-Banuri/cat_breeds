package com.catbreeds.example.di;

import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelper;
import com.catbreeds.example.domain.repositories.CatBreedsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class RepoModule_ProvideCatRepositoryFactory implements Factory<CatBreedsRepository> {
  private final Provider<CatBreedApiServiceHelper> catsApiServiceProvider;

  public RepoModule_ProvideCatRepositoryFactory(
      Provider<CatBreedApiServiceHelper> catsApiServiceProvider) {
    this.catsApiServiceProvider = catsApiServiceProvider;
  }

  @Override
  public CatBreedsRepository get() {
    return provideCatRepository(catsApiServiceProvider.get());
  }

  public static RepoModule_ProvideCatRepositoryFactory create(
      Provider<CatBreedApiServiceHelper> catsApiServiceProvider) {
    return new RepoModule_ProvideCatRepositoryFactory(catsApiServiceProvider);
  }

  public static CatBreedsRepository provideCatRepository(CatBreedApiServiceHelper catsApiService) {
    return Preconditions.checkNotNullFromProvides(RepoModule.INSTANCE.provideCatRepository(catsApiService));
  }
}
