package com.catbreeds.example.di;

import com.catbreeds.example.data.services.CatBreedService;
import com.catbreeds.example.data.services.catbreeds.CatBreedApiServiceHelper;
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
public final class ServiceHelperModule_ProvideCatApiServiceHelperFactory implements Factory<CatBreedApiServiceHelper> {
  private final Provider<CatBreedService> apiServiceProvider;

  public ServiceHelperModule_ProvideCatApiServiceHelperFactory(
      Provider<CatBreedService> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public CatBreedApiServiceHelper get() {
    return provideCatApiServiceHelper(apiServiceProvider.get());
  }

  public static ServiceHelperModule_ProvideCatApiServiceHelperFactory create(
      Provider<CatBreedService> apiServiceProvider) {
    return new ServiceHelperModule_ProvideCatApiServiceHelperFactory(apiServiceProvider);
  }

  public static CatBreedApiServiceHelper provideCatApiServiceHelper(CatBreedService apiService) {
    return Preconditions.checkNotNullFromProvides(ServiceHelperModule.INSTANCE.provideCatApiServiceHelper(apiService));
  }
}
