package com.catbreeds.example.di;

import com.catbreeds.example.data.services.CatBreedService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ServiceModule_ProvideCatsServiceFactory implements Factory<CatBreedService> {
  private final Provider<Retrofit> retrofitProvider;

  public ServiceModule_ProvideCatsServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CatBreedService get() {
    return provideCatsService(retrofitProvider.get());
  }

  public static ServiceModule_ProvideCatsServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new ServiceModule_ProvideCatsServiceFactory(retrofitProvider);
  }

  public static CatBreedService provideCatsService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(ServiceModule.INSTANCE.provideCatsService(retrofit));
  }
}
