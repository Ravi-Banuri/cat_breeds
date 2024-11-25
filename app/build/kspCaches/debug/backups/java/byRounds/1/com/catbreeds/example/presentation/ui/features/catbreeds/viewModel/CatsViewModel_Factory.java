package com.catbreeds.example.presentation.ui.features.catbreeds.viewModel;

import com.catbreeds.example.domain.usecase.cats.CatBreedsFetchUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class CatsViewModel_Factory implements Factory<CatsViewModel> {
  private final Provider<CatBreedsFetchUseCase> catUseCaseProvider;

  private final Provider<SharedDataRepository> repositoryProvider;

  public CatsViewModel_Factory(Provider<CatBreedsFetchUseCase> catUseCaseProvider,
      Provider<SharedDataRepository> repositoryProvider) {
    this.catUseCaseProvider = catUseCaseProvider;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CatsViewModel get() {
    return newInstance(catUseCaseProvider.get(), repositoryProvider.get());
  }

  public static CatsViewModel_Factory create(Provider<CatBreedsFetchUseCase> catUseCaseProvider,
      Provider<SharedDataRepository> repositoryProvider) {
    return new CatsViewModel_Factory(catUseCaseProvider, repositoryProvider);
  }

  public static CatsViewModel newInstance(CatBreedsFetchUseCase catUseCase,
      SharedDataRepository repository) {
    return new CatsViewModel(catUseCase, repository);
  }
}
