package com.catbreeds.example.di;

import com.catbreeds.example.domain.repositories.CatBreedsRepository;
import com.catbreeds.example.domain.usecase.cats.CatBreedsFetchUseCase;
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
public final class UseCaseModule_ProvideCatUseCaseFactory implements Factory<CatBreedsFetchUseCase> {
  private final Provider<CatBreedsRepository> catsRepoProvider;

  public UseCaseModule_ProvideCatUseCaseFactory(Provider<CatBreedsRepository> catsRepoProvider) {
    this.catsRepoProvider = catsRepoProvider;
  }

  @Override
  public CatBreedsFetchUseCase get() {
    return provideCatUseCase(catsRepoProvider.get());
  }

  public static UseCaseModule_ProvideCatUseCaseFactory create(
      Provider<CatBreedsRepository> catsRepoProvider) {
    return new UseCaseModule_ProvideCatUseCaseFactory(catsRepoProvider);
  }

  public static CatBreedsFetchUseCase provideCatUseCase(CatBreedsRepository catsRepo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideCatUseCase(catsRepo));
  }
}
